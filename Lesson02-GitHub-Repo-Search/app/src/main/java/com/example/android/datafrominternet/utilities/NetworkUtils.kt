/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.datafrominternet.utilities

import android.net.Uri
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * These utilities will be used to communicate with the network.
 */
object NetworkUtils {

    internal val GITHUB_BASE_URL = "https://api.github.com/search/repositories"

    internal val PARAM_QUERY = "q"

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    internal val PARAM_SORT = "sort"
    internal val sortBy = "stars"

    /**
     * Builds the URL used to query Github.
     *
     * @param githubSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    fun buildUrl(githubSearchQuery: String): URL? {
        val uri = Uri.parse(GITHUB_BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build()
        return try { URL(uri.toString()) } catch (e: MalformedURLException) { null }
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     */
    fun getResponseFromHttpUrl(url: URL): String? {
        var urlConnection: HttpURLConnection? = null
        try {
            urlConnection = url.openConnection() as? HttpURLConnection
            val `in` = urlConnection?.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if (hasInput) {
                scanner.next()
            } else {
                null
            }
        } finally {
            urlConnection?.disconnect()
        }
    }
}