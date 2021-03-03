package com.appogee.etc.callback

interface CallbackTrxPaymentListener {
    fun onAuthenticatedUsingFingerprint()
    fun onAuthenticatedUsingPassword(password: String)
    fun onCancel()
}