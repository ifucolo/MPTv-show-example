package com.example.com.data.mapper

import com.example.com.data.factory.TvShowFactory
import com.example.com.data.model.TvShowEntity
import com.example.com.domain.model.TvShow
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TvShowMapperTest {

    private val mapper = TvShowMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = TvShowFactory.makeTvShowEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = TvShowFactory.makeTvShow()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }


    private fun assertEqualData(entity: TvShowEntity, model: TvShow) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.overview, model.overview)
        assertEquals(entity.posterImage, model.posterImage)
        assertEquals(entity.title, model.title)
        assertEquals(entity.voteAverage, model.voteAverage)
    }
}