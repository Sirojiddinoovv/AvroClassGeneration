package uz.nodir.avroclassgenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AvroClassGeneratorApplication

fun main(args: Array<String>) {
    runApplication<AvroClassGeneratorApplication>(*args)
}
