package com.example.rx

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RxApplicationTests {

	@Test
	fun contextLoads() {
		var test = mutableListOf<Int>()
		val array: IntArray = intArrayOf(100, 180, 360, 100, 270)
		val result = solution2(array)
		val ttt = ""
	}

	fun solution2(weights: IntArray): Long {
		var answer: Long = 0
		for (i: Int in 0..weights.size -2) {
			for (j: Int in i+1..weights.size -1) {
				var result: Float = 0f
				val x = weights[i].toFloat()
				val y = weights[j].toFloat()
				if (x >= y) {
					result = x / y
				}
				else {
					result = y /x
				}
				if (result in listOf<Float>(
						(1).toFloat(),
						(2).toFloat(),
						(1.5).toFloat(),
						((4).toFloat() / (3).toFloat()),
					)) {
					answer = answer + 1
				}
			}
		}
		return answer
	}


	fun solution(weights: IntArray): Long {
		var answer: Long = 0
		for (i: Int in 0..weights.size -1) {
			for (j in i+1..weights.size -1) {
				if (weights[i] == weights[j]) {
					answer = answer + 1
				}
				else {
					var iList = mutableListOf<Int>()
					for (meter: Int in 2..4) {
						iList.add(weights[i] * meter)
					}
					var jList = mutableListOf<Int>()
					for (meter: Int in 2..4) {
						jList.add(weights[j] * meter)
					}
					for (item in iList) {
						if (jList.contains(item)) {
							answer = answer + 1
							break
						}
					}
				}
			}
		}
		return answer
	}

}
