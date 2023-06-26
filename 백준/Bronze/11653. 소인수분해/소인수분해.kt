import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N:Int = br.readLine().toInt()
    var nextNo = 2
    while(N != 1){
        if(N % nextNo == 0){
            println(nextNo)
            N /= nextNo
        }
        else{
            nextNo++
        }
    }
}