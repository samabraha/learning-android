package com.develogica.top10downloader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.lang.Exception

class ParseApplications {
    private val TAG = "ParseApplications"

    val applications = ArrayList<FeedEntry>()

    fun parse(XMLData : String ) : Boolean {
        Log.d(TAG, "parse called with $XMLData")

        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(XMLData.reader())
            var eventType = xpp.eventType
            var currentRecord = FeedEntry()
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp.name?.lowercase()
                when (eventType) {
                    XmlPullParser.START_TAG -> {
//                        Log.d(TAG, "parse: Starting tag for $tagName")
                        if (tagName == "item") {
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
//                        Log.d(TAG, "parse: Ending tag for $tagName")
                        if (inEntry) {
                            when (tagName) {
                                "item" -> {
                                    applications.add(currentRecord)
                                    inEntry = false
                                    currentRecord = FeedEntry()
                                }

                                "title" -> currentRecord.title = textValue
                                "description" -> currentRecord.description = textValue
                                "link" -> currentRecord.link = textValue
                                "guid" -> currentRecord.guid = textValue
                                "pubdate" -> currentRecord.publicationDate = textValue

                            }
                        }
                    }
                }

                eventType = xpp.next()

            }
//
//            for (app in applications ) {
//                Log.d(TAG, "*******************")
//                Log.d(TAG, app.toString())
//            }
        } catch (exception : Exception) {
            exception.printStackTrace()
            status = false
        }
        return status
    }

}