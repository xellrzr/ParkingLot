class Employee(val id: Int, val name: String, val lastName: String, val telNum: String, val email: String) {

    fun printData() {
        println("Employee $id")
        println("full name: $name $lastName")
        println("tel. num: $telNum")
        println("email: $email")
    }
}
fun main() {
    val count = readLine()!!.toInt()
    // create reference to the constructor Employee
    val employeeCreator: (Int, String, String, String, String) -> Employee = ::Employee
    createEmployees(employeeCreator, count)
}
fun createEmployees(employeeCreator: (Int, String, String, String, String) -> Employee, count: Int) {
    for (i in 1..count) {
        val (name, lastName, telNum, email) = readLine()!!.split(' ').toList()
        // write your code here
        val newEmployee = Employee(id = i, name, lastName, telNum, email).printData()

    }
}
