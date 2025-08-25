package com.abhishek.githubusers.data.mapper

import com.abhishek.githubusers.util.FakeUsersData
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDetailsMapperImplTest {

    private val mapper = UserDetailsMapperImpl()

    @Test
    fun `userDetailsDtoToEntity should map all fields correctly`() {
        val dto = FakeUsersData.getFakeUserDetails()

        val entity = mapper.userDetailsDtoToEntity(dto)

        assertEquals(dto.login, entity.login)
        assertEquals(dto.avatarUrl, entity.avatarUrl)
        assertEquals(dto.bio, entity.bio)
        assertEquals(dto.blog, entity.blog)
        assertEquals(dto.company, entity.company)
        assertEquals(dto.email, entity.email)
        assertEquals(dto.followers, entity.followers)
        assertEquals(dto.following, entity.following)
        assertEquals(dto.location, entity.location)
        assertEquals(dto.name, entity.name)
        assertEquals(dto.publicRepos, entity.publicRepos)
    }
}
