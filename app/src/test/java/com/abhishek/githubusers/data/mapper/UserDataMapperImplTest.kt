package com.abhishek.githubusers.data.mapper

import com.abhishek.githubusers.ui.utils.PreviewUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class UserDataMapperImplTest {

    private val mapper = UserDataMapperImpl()

    @Test
    fun `dtoToEntity should map correctly`() {
        val dto = PreviewUtils.getUserData()

        val entity = mapper.dtoToEntity(dto)

        assertEquals(dto.id, entity.id)
        assertEquals(dto.login, entity.login)
        assertEquals(dto.avatarUrl, entity.avatarUrl)
    }
}
