package alirezat775.lib.carouselview

import alirezat775.lib.carouselview.helper.ViewHelper
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity

/**
 * Author:  Alireza Tizfahm Fard
 * Date:    2019-06-14
 * Email:   alirezat775@gmail.com
 */

class Carousel constructor(
    private var appCompatActivity: AppCompatActivity,
    @NonNull private var carouselView: CarouselView,
    @NonNull private var adapter: CarouselAdapter
) {

    private var manager: CarouselLayoutManager? = null

    init {
        carouselView.layoutManager = getManager()
        carouselView.adapter = adapter
        carouselView.autoScroll(false)
    }

    fun setOrientation(@CarouselView.CarouselOrientation orientation: Int, reverseLayout: Boolean) {
        manager = CarouselLayoutManager(appCompatActivity, orientation, reverseLayout)
        carouselView.layoutManager = manager
        val padding: Int
        when (orientation) {
            CarouselView.HORIZONTAL -> {
                padding = ViewHelper.getScreenWidth() / 4
                carouselView.setPadding(padding, 0, padding, 0)
            }
            CarouselView.VERTICAL -> {
                padding = ViewHelper.getScreenHeight() / 4
                carouselView.setPadding(0, padding, 0, padding)
            }
        }
    }

    /**
     * @param scaleView enable scaleView item
     */
    fun scaleView(scaleView: Boolean) {
        getManager()?.scaleView(scaleView)
    }

    /**
     * @return CarouselLayoutManager
     */
    private fun getManager(): CarouselLayoutManager? {
        if (manager == null) throw NullPointerException("please call setOrientation method")
        return manager
    }

    /**
     * @param items list items should be add to carousel
     */
    fun addAll(items: MutableList<CarouselModel>) {
        adapter.addAll(items)
    }

    /**
     * @param item one item should be add to carousel
     */
    fun add(item: CarouselModel) {
        adapter.operation(item, CarouselAdapter.ADD)
    }

    /**
     * @param item list items should be remove to carousel
     */
    fun remove(item: CarouselModel) {
        adapter.operation(item, CarouselAdapter.REMOVE)
    }

    /**
     * @param currentPosition
     */
    fun setCurrentPosition(currentPosition: Int) {
        carouselView.scrollToPosition(currentPosition)
    }

    /**
     * @return current item position
     */
    fun getCurrentPosition(): Int {
        return carouselView.getCurrentPosition()
    }

    /**
     * pause auto scrolling
     */
    fun pauseAutoScroll() {
        carouselView.pauseAutoScroll()
    }

    /**
     * resume auto scrolling
     */
    fun resumeAutoScroll() {
        carouselView.resumeAutoScroll()
    }

    /**
     * @param autoScroll
     * @param delayMillis
     * @param loopMode
     */
    fun autoScroll(autoScroll: Boolean, delayMillis: Long, loopMode: Boolean) {
        carouselView.autoScroll(autoScroll)
        carouselView.delayMillis(delayMillis)
        carouselView.loopMode(loopMode)
    }

    /**
     * @param enableSlider enable slider mode
     */
    fun enableSlider(enableSlider: Boolean) {
        adapter.enableSlider(enableSlider)
    }

}