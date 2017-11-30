package chatt.fullstack.backend

import chatt.fullstack.shared.Post

interface PostDao {

    /**
     * @return post with given id from storage or null if none exist.
     */
    fun fetchOneById(id: String): Post?

    /**
     * @return all posts.
     */
    fun fetchAll(): Collection<Post>

    /**
     * Inserts post into storage or replaces one if id already in use.
     * @return true if an existing post was replaced.
     */
    fun insertOrReplace(post: Post): Boolean

    /**
     * Deletes post with given id if such exist.
     * @return true if a post was deleted.
     */
    fun deleteById(id: String): Boolean
}