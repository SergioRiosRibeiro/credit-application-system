package me.dio.creditapplicationsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.creditapplicationsystem.entity.Address
import me.dio.creditapplicationsystem.entity.Customer
import me.dio.creditapplicationsystem.exception.BusinessException
import me.dio.creditapplicationsystem.repository.CustomerRepository
import me.dio.creditapplicationsystem.service.impl.CustomerService
import org.apache.el.stream.Optional
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import kotlin.random.Random

//@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK
    lateinit var customerRespository: CustomerRepository

    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRespository.save(any()) } returns fakeCustomer
        //when
        val actual: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRespository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakedId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakedId)
        every { customerRespository.findById(fakedId) } returns java.util.Optional.of(fakeCustomer)
        //when
        val actual: Customer = customerService.findById(fakedId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRespository.findById(fakedId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random.nextLong()
        every { customerRespository.findById(fakeId) } returns java.util.Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRespository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random.nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRespository.findById(fakeId) } returns java.util.Optional.of(fakeCustomer)
        every { customerRespository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRespository.findById(fakeId) }
        verify(exactly = 1) { customerRespository.delete(fakeCustomer) }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "Person",
            lastName: String = "Anyone",
            cpf: String = "50011733373",
            email: String = "person@anemail.com",
            password: String = "25986",
            zipCode: String = "70124",
            street: String = "Person's Street",
            income: BigDecimal = BigDecimal.valueOf(7500.0),
            id: Long = 1L
        ) = Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }
}


