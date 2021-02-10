package id.airham.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.airham.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import java.util.concurrent.Executors

object NetworkHelper {
    private val TAG = NetworkHelper::class.java.simpleName

    fun <T> call(call: Call<T>): LiveData<ApiResponse<T>> {
        EspressoIdlingResource.increment()
        val returnVal = MutableLiveData<ApiResponse<T>>()

        Executors.newFixedThreadPool(5).execute {
            val response = call.execute()

            if (response.isSuccessful) {
                response.body()?.let {
                    returnVal.postValue(ApiResponse.success(it))
                }
            } else {
                Log.e(TAG, "Error: " + response.errorBody()?.string())
                returnVal.postValue(null)
            }

            decrementIdlingResource()
        }

        return returnVal
    }

    private fun decrementIdlingResource() {
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }
}