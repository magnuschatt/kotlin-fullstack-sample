package chatt.fullstack.backend

import chatt.fullstack.shared.Post
import java.util.concurrent.ConcurrentHashMap

class InMemoryPostDao : PostDao {

    private val posts: MutableMap<String, Post> = ConcurrentHashMap()

    override fun findOneById(id: String) = posts[id]
    override fun insertOrReplace(post: Post) = posts.put(post.id, post) != null
    override fun deleteById(id: String) = posts.remove(id) != null
}