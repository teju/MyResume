package com.amazing.portfolio.etc.callback

interface CallbackTrxPaymentListener {
    fun onAuthenticatedUsingFingerprint()
    fun onAuthenticatedUsingPassword(password: String)
    fun onCancel()
}