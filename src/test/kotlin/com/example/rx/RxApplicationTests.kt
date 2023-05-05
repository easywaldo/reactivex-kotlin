package com.example.rx

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
class RxApplicationTests {

	@Test
	fun test2() {
//		val result = solution2(intArrayOf(1, 2, 3, 4))
		val result = solution2(intArrayOf(2,4,8))
		val test = ""
	}

	fun solution2(common: IntArray): Int {
		var ratioList = mutableListOf<Int>()
		var subList = mutableListOf<Int>()
		for (n: Int in common.reversed()) {
			val index = common.indexOf(n)
			if (index != 0) {
				ratioList.add(n / common.get(index -1))
				subList.add(n - common.get(index - 1))
			}
		}

		var firstNumber = common.first()
		subList.forEach {
			firstNumber = firstNumber + it
		}
		if (firstNumber == common.last() && subList.first() == subList.last()) {
			return common.last() + subList.last()
		}
		else {
			return common.last() * ratioList.last()
		}
	}


	@Test
	fun contextLoads() {
		val nList = intArrayOf(2,4,6,8)
		for (n in nList.reversed()) {

		}
		val testList = listOf(1,2,3)
		testList.size
		nList.size


		val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
		val createdAt = LocalDate.parse("2022.05.02", formatter)
		var term = "A 6".split(" ")
		if (createdAt.plusMonths(6).isAfter(LocalDate.now())) {

		}
		var answer2: IntArray = intArrayOf()
		answer2 += 1
		answer2 += 2
		var tet = ""


		var terms = listOf<String>("A 6", "B 12", "C 3")
		var term_map = mutableMapOf<String, Long>()
		terms.forEach {
			val key = it.split(" ")[0]
			val value = it.split(" ")[1].toLong()
			term_map[key] = value
		}
		var term_map_2 = terms.map {
			val key = it.split(" ")[0]
			val value = it.split(" ")[1].toLong()
			mapOf(key to value)
		}
		var tt = ""
		val s = term_map.get("A")?.toLong()
		val x = ""

		solution("2022.05.19", arrayOf("A 6","B 12","C 3"), arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"))
	}

	fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
		var answer: IntArray = intArrayOf()
		var term_map = mutableMapOf<String, Long>()
		terms.forEach {
			val key = it.split(" ")[0]
			val value = it.split(" ")[1].toLong()
			term_map[key] = value
		}

		var counter: Int = 0
		for (privacy: String in privacies) {
			val privacy_date = privacy.split(" ")[0]
			val privacy_type = privacy.split(" ")[1]
			val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy.MM.dd")
			val nowDate = java.time.LocalDate.parse(today, formatter)
			var term_range: Long = term_map.get(privacy_type)!!.toLong()

			var privacyDateYear = privacy_date.split(".")[0].toInt()
			var privacyDateMonth = privacy_date.split(".")[1].toInt()
			var privacyDateDay = privacy_date.split(".")[2].toInt()
			var afterYear = 0L
			var afterMonth = privacyDateMonth + term_range
			if (afterMonth > 12) {
				afterYear = afterMonth / 12
				afterMonth = afterMonth % 12
			}
			val createdAt = LocalDate.of(privacyDateYear + afterYear.toInt(), privacyDateMonth + afterMonth.toInt(),  privacyDateDay)

			if (createdAt.isAfter(nowDate)) {
				answer += (counter + 1)
			}
			counter += 1
		}
		return answer
	}

}
