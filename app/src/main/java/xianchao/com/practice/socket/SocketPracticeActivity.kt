package xianchao.com.practice.socket

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.style.ClickableSpan
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_socket_practice.*
import showToast
import xianchao.com.basiclib.utils.checkIsEmpty
import xianchao.com.practice.R
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class SocketPracticeActivity : AppCompatActivity() {


    companion object {
        var  handler = Handler(Looper.getMainLooper())
        lateinit var tvServerConsole: TextView
        lateinit var tvClientConsole: TextView
        fun serverConsole(msg: String) {
            handler.post {
                tvServerConsole.append(msg + "\\\n")
            }
        }

        fun clientConsole(msg: String) {
            handler.post {
                tvClientConsole.append(msg + "\\\n")
            }
        }
    }


    var socket: Socket? = null
    var writer: OutputStream? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket_practice)
        tvClientConsole = tv_client_console
        tvServerConsole = tv_server_console

        btn_connect_server.setOnClickListener {
            val ip = et_ip.text.toString()
            val port = et_port.text.toString()
            if (ip.checkIsEmpty()) {
                showToast("请输入 ip")
                return@setOnClickListener
            }
            if (port.checkIsEmpty()) {
                showToast("请输入端口")
                return@setOnClickListener
            }

            Thread {

                try {

                    clientConsole("创建 socket")
                    socket = Socket(ip, port.toInt())
                    socket!!.keepAlive = true
                     writer = socket!!.getOutputStream()
                    val reader = BufferedReader(InputStreamReader(socket!!.getInputStream()))
                    writer!!.write("test".toByteArray())
                    writer!!.flush()
                    clientConsole("连接到服务器")
                    while (true) {
                        try{

                            var readContent = reader.readLine()
                            if (readContent != null) {
                                clientConsole("服务器返回-》" + readContent)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }.start()
        }

        initBtnSend()

        initBtnStartServer()


    }

    private fun initBtnStartServer() {
        btn_start_server.setOnClickListener {

            Thread {

                val serverSocket = ServerSocket(10101)
                serverConsole(serverSocket.inetAddress.address.toString())
                serverConsole(serverSocket.inetAddress.hostAddress)
                serverConsole(serverSocket.inetAddress.hostName)
                handler.post {
                    tv_server_info.text = serverSocket.inetAddress.address.toString() + ":" + serverSocket.localPort
                }

                val accept = serverSocket.accept()

                val reader = BufferedReader(InputStreamReader(accept.getInputStream()))

                while (true){

                    try {

                        val readLine = reader.readLine()
                        if (readLine != null){
                            serverConsole("收到-》" + readLine)
                            var stream = accept.getOutputStream()
                            stream.write(("服务器返回" + readLine + "\n").toByteArray())
                            stream.flush()
                        }
                    }catch(e: Exception) {
                        e.printStackTrace()
                    }
                }

            }.start()

        }


    }

    private fun initBtnSend() {
        btn_send_to_server.setOnClickListener {

            if (socket == null) {
                clientConsole("未连接到服务器")
                return@setOnClickListener
            }
            val content = et_client_content.text.toString()
            if (content.checkIsEmpty()) {
                showToast("需要输入内容")
                return@setOnClickListener
            }

            Thread(){
                writer!!.write((content+ "\n").toByteArray())
                writer!!.flush()

            }.start()

//            thread {
//                writer!!.write(content)
//                writer!!.flush()
//            }
        }
    }

}
