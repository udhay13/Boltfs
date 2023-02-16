package org.monora.uprotocol.client.android.database.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.monora.uprotocol.client.android.model.ListItem

@Parcelize
@Entity(
    tableName = "webTransfer"
)
data class WebTransfer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uri: Uri,
    val name: String,
    val mimeType: String,
    val size: Long,
    val dateCreated: Long = System.currentTimeMillis(),
) : Parcelable, ListItem {
    override val listId: Long
        get() = uri.hashCode().toLong() + javaClass.hashCode()
}
