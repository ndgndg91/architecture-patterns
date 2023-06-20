package com.ndgndg91.byfeature.user.controller

import com.ndgndg91.byfeature.user.service.UserService
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [UserController::class])
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun test_should_return_400_singUp_empty_email() {
        // given
        //language=json
        val json = "{\n  \"email\": \"\",\n  \"password\": \"pass\"\n}"

        // when
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andDo(MockMvcResultHandlers.log())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

        then(userService).should(never()).signUp(any())
    }

    @Test
    fun test_should_return_400_invalid_query_param() {
        // given

        // when - then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/users/100")
                .queryParam("size", "-10")
        ).andDo(MockMvcResultHandlers.log())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun test_should_return_400_invalid_path_variable() {
        // given

        // when - then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/users/-10")
                .queryParam("size", "100")
        ).andDo(MockMvcResultHandlers.log())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
}