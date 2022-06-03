package parking


class ParkingLot(size: Int) {
    data class Car(val plate: String, val color: String)
    data class Spot(val spotNumber: Int, val isFree: Boolean, val car: Car?)

    private val parkingData = Array(size) { Spot(it + 1, true, null) }
    var parkSpotCount = 0

    fun park(car: Car) {
        val firstAvailableSpot = parkingData.first { it.isFree }
        parkingData[firstAvailableSpot.spotNumber - 1] = Spot(firstAvailableSpot.spotNumber, false, car)
        println(car.color + " car parked in spot " + firstAvailableSpot.spotNumber + ".")
        parkSpotCount++
    }

    fun leave(spotNumber: Int) {
        if (parkingData[spotNumber - 1].isFree)
            println("There is no car in spot $spotNumber.")
        else {
            println("Spot $spotNumber is free.")
            parkingData[spotNumber - 1] = Spot(spotNumber, true, null)
            parkSpotCount--
        }
    }

    fun status() {
        if (parkSpotCount == 0) {
            println("Parking lot is empty.")
        } else {
            for (i in parkingData) {
                if (!i.isFree) println("${i.spotNumber}" + " " + "${i.car?.plate}" + " " + "${i.car?.color}")
            }
        }
    }

    fun regByColor(color: String) {
        val regColor = parkingData.filter { it.car?.color.equals(color, ignoreCase = true)
        }.map { it.car?.plate }.joinToString()
        println(regColor.ifEmpty { "No cars with color $color were found." })
    }

    fun spotByColor(color: String) {
        val spotColor = parkingData.filter { it.car?.color.equals(color, ignoreCase = true)
        }.map { it.spotNumber }.joinToString()
        println(spotColor.ifEmpty { "No cars with color $color were found." })
    }

    fun spotByReg(plate: String) {
        val spotReg = parkingData.filter { it.car?.plate.equals(plate, ignoreCase = true)
        }.map { it.spotNumber }.joinToString()
        println(spotReg.ifEmpty { "No cars with registration number $plate were found." })
    }

}

fun main() {
    var parkingLot: ParkingLot? = null
    var size = 0
    while(true) {
        val command = readLine()!!.split(" ")
        if (command[0] == "exit") break
        else if (command[0] == "create") {
            size = command[1].toInt()
            parkingLot = ParkingLot(size)
            println("Created a parking lot with $size spots.")
        } else if (parkingLot == null)
            println("Sorry, a parking lot has not been created.")
        else when (command[0]) {
            "park" ->
                if (parkingLot!!.parkSpotCount < size) {
                    parkingLot!!.park(ParkingLot.Car(command[1], command[2]))
                } else {
                    println("Sorry, the parking lot is full.")
                }
            "leave" -> parkingLot!!.leave(command[1].toInt())
            "status" -> parkingLot!!.status()
            "reg_by_color" -> parkingLot.regByColor(command[1])
            "spot_by_color" -> parkingLot.spotByColor(command[1])
            "spot_by_reg" -> parkingLot.spotByReg(command[1])
        }

    }
}    
