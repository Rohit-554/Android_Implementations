    private fun generateUPIqr(vpa: String, amount: Long, name: String) {
        val url = "upi://pay?pa=" +
                vpa +
                "&am=$amount" +
                "&pn=${encodeSpacesInName(name)}" +
                "&cu=INR" +
                "&mode=02" +
                "&orgid=000000"
        try {
            val bitmap = TextToImageBarcode.textToImageEncode(url)
            binding.imagetest.setImageBitmap(bitmap)
            binding.imagetest.invalidate()
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
