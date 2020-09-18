package org.xapps.apps.mangaxdatabase.services.utils

import android.content.Context
import io.reactivex.Single
import java.io.File


object FileUtils {

    @JvmStatic
    fun copyPosterToAppFolderAsync(context: Context, source: String?, fileName: String): Single<Boolean> {
        return Single.fromCallable {
            copyPosterToAppFolder(context, source, fileName)
        }
    }

    @JvmStatic
    fun copyPosterToAppFolder(context: Context, source: String?, fileName: String): Boolean {
        return source?.let {
            val extension = File(source).extension
            val destiny = PathUtils.appPosterPathByName(context, "$fileName.$extension")
            val inFile = File(source)
            val outFile = File(destiny)
            val inStream = inFile.inputStream()
            val outStream = outFile.outputStream()
            val buffer = ByteArray(1024)
            var reed = inStream.read(buffer)
            while (reed != -1) {
                outStream.write(buffer, 0, reed)
                reed = inStream.read(buffer)
            }
            inStream.close()
            outStream.flush()
            outStream.close()

            true
        } ?: run {
            false
        }
    }

}