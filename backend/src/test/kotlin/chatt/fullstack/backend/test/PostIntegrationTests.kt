package chatt.fullstack.backend.test

import chatt.fullstack.shared.Post
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import khttp.delete
import khttp.get
import khttp.put
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class PostIntegrationTests {

    private val backendBaseUrl = "http://localhost:9000"
    private val jsonMapper = ObjectMapper().registerKotlinModule()

    private fun backend(path: String) = backendBaseUrl + path
    private fun ByteArray.toPost() = jsonMapper.readValue<Post>(this)
    private fun ByteArray.toPostArray() = jsonMapper.readValue<Array<Post>>(this)
    private fun Post.toMap() = jsonMapper.convertValue<Map<String, String>>(this)
    private fun uuid() = UUID.randomUUID().toString().replace("-","")

    private fun putPost(post: Map<String, Any>) = put(backend("/post"), json = post)
    private fun putPost(post: Post) = putPost(post.toMap())
    private fun getPost(id: String) = get(backend("/post/$id"))
    private fun getAllPosts() = get(backend("/post"))
    private fun deletePost(id: String) = delete(backend("/post/$id"))

    @Test
    fun `test put with malformed json`() {
        val put = putPost(mapOf("id" to "123", "content" to "title is missing"))
        assertEquals(422, put.statusCode) // Unprocessable Entity
    }

    @Test
    fun `test get on nonexistent post`() {
        assertEquals(404, getPost(uuid()).statusCode)
    }

    @Test
    fun `test delete on nonexistent post`() {
        assertEquals(404, deletePost(uuid()).statusCode)
    }

    @Test
    fun `test successful put-create and put-replace`() {
        val id = uuid()

        val post1 = Post(id, "xxx", "Content1")
        val put1 = putPost(post1)
        assertEquals(put1.statusCode, 201) // Created
        assertEquals(post1, getPost(id).content.toPost())

        val post2 = Post(id, "xxx", "Content1")
        val put2 = putPost(post2)
        assertEquals(put2.statusCode, 200) // OK
        assertEquals(post2, getPost(id).content.toPost())

        deletePost(post1.id)
        deletePost(post2.id)
    }

    @Test
    fun `test successful put, get and delete`() {
        val id = uuid()
        val putPost = Post(id, "Title1", "Content1")
        val put = putPost(putPost)
        assertEquals(put.statusCode, 201) // Created

        val get = getPost(id)
        assertEquals(get.statusCode, 200) // OK
        val getPost = get.content.toPost()
        assertEquals(putPost, getPost)

        val delete = deletePost(id)
        assertEquals(204, delete.statusCode) // No Content
    }

    @Test
    fun `test successful put, get-all and delete`() {

        val inPosts = setOf(
                Post(uuid(), "Title2", "Content2"),
                Post(uuid(), "Title3", "Content3"),
                Post(uuid(), "Title4", "Content4"),
                Post(uuid(), "Title5", "Content5")
        )

        inPosts.forEach { post ->
            val put = putPost(post)
            assertEquals(put.statusCode, 201) // Created
        }

        val getAll = getAllPosts()
        assertEquals(getAll.statusCode, 200) // OK
        val outPosts = getAll.content.toPostArray().toSet()
        assertTrue(outPosts.containsAll(inPosts))

        inPosts.forEach { deletePost(it.id) }
    }


}