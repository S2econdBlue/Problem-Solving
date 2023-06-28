import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val A: Int = br.readLine().toInt()
    val B: Int = br.readLine().toInt()
    print(A * B)
}
