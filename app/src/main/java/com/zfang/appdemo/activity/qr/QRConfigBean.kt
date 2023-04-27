package com.zfang.appdemo.activity.qr

class QRConfigBean (
        val url: String = "https://www.baidu.com/",
        val qrCodeArea: QRCodeArea = QRCodeArea(),
        val inviteTextArea: InviteTextArea = InviteTextArea(),
)

data class QRCodeArea(
        val positionX: Int = 163,
        val positionY: Int = 653,
        val width: Int = 180,
        val height: Int = 180,
)

data class InviteTextArea(
        val positionX: Int = 250,
        val positionY: Int = 456,
        val textSize: Float = 16f,
        val textColor: String = "#FFFFFF"
)