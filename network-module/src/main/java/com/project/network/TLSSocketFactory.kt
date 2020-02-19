package com.project.network

import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory


class TLSSocketFactory(private val factory: SSLSocketFactory) : SSLSocketFactory() {

    private val TLS_V12_ONLY = arrayOf("TLSv1.2")

    override fun getDefaultCipherSuites() = factory.defaultCipherSuites
    override fun getSupportedCipherSuites() = factory.supportedCipherSuites

    override fun createSocket(socket: Socket?, host: String?, port: Int, autoClose: Boolean)
            = patch(factory.createSocket(socket, host, port, autoClose))

    override fun createSocket(host: String?, port: Int)
            = patch(factory.createSocket(host, port))

    override fun createSocket(host: String?, port: Int, localHost: InetAddress?, localPort: Int)
            = patch(factory.createSocket(host, port, localHost, localPort))

    override fun createSocket(localHost: InetAddress?, port: Int)
            = patch(factory.createSocket(localHost, port))

    override fun createSocket(address: InetAddress?, port: Int, localAddress: InetAddress?, localPort: Int)
            = patch(factory.createSocket(address, port, localAddress, localPort))


    private fun patch(socket: Socket): Socket {
        if (socket is SSLSocket) {
            socket.setEnabledProtocols(TLS_V12_ONLY)
        }

        return socket
    }

}