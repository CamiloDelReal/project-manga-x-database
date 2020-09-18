package org.xapps.apps.mangaxdatabase.services.utils

import android.content.Context
import android.os.Environment
import java.io.File


object PathUtils {

    private const val PATH_SEPARATOR = "/"
    private const val POSTERS_FOLDER = "posters"
    private const val TMP_FOLDER = "temp"

    @JvmStatic
    fun prepareFolders(path: String) {
        File(path).mkdir()
    }

    @JvmStatic
    fun getAppStorageFolder(context: Context): String {
        var storageFolder: File?

        val state = Environment.getExternalStorageState()
        storageFolder = if (Environment.MEDIA_MOUNTED == state) {
            context.getExternalFilesDir(null) ?: context.filesDir
        } else {
            context.filesDir
        }
        return storageFolder!!.absolutePath
    }

    @JvmStatic
    fun appTempFolderPath(context: Context): String =
        (getAppStorageFolder(context) + PATH_SEPARATOR + TMP_FOLDER)

    @JvmStatic
    fun appTempPathByName(context: Context, name: String): String =
        (appTempFolderPath(context) + PATH_SEPARATOR + name)

    @JvmStatic
    fun appPostersFolderPath(context: Context): String =
        (getAppStorageFolder(context) + PATH_SEPARATOR + POSTERS_FOLDER)

    @JvmStatic
    fun appPosterPathByName(context: Context, name: String): String =
        (appPostersFolderPath(context) + PATH_SEPARATOR + name)

}