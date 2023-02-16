package org.monora.uprotocol.client.android.data

import android.net.Uri
import androidx.lifecycle.LiveData
import org.monora.uprotocol.client.android.database.WebTransferDao
import org.monora.uprotocol.client.android.database.model.WebTransfer
import org.monora.uprotocol.client.android.util.Networks
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebDataRepository @Inject constructor(
    private val webTransferDao: WebTransferDao,
) {
    private val sharedContents = mutableListOf<Any>()

    val isServing
        get() = sharedContents.isNotEmpty()

    fun clear() {
        synchronized(sharedContents) {
            sharedContents.clear()
        }
    }

    fun getList() = sharedContents.toList()

    fun getReceivedContent(id: Int): LiveData<WebTransfer> = webTransferDao.get(id)

    suspend fun getReceivedContent(uri: Uri): WebTransfer? = webTransferDao.get(uri)

    fun getReceivedContents() = webTransferDao.getAll()

    suspend fun insert(webTransfer: WebTransfer) = webTransferDao.insert(webTransfer)

    fun getNetworkInterfaces() = Networks.getInterfaces()

    suspend fun remove(transfer: WebTransfer) {
        webTransferDao.remove(transfer)
    }

    fun serve(list: List<Any>) {
        synchronized(sharedContents) {
            sharedContents.clear()
            sharedContents.addAll(list)
        }
    }
}
