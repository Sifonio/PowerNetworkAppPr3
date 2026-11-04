package com.example.powernetworkapppr3.data

data class NetworkLoad(val id: Int, val nodeName: String, val loadMw: Double)

class LocalNetworkDataSource {
    private val data = mutableListOf<NetworkLoad>()
    fun getAll(): List<NetworkLoad> = data.toList()
    fun insert(item: NetworkLoad) { data.add(item) }
}

class NetworkRepository(private val local: LocalNetworkDataSource) {
    fun getAll(): List<NetworkLoad> = local.getAll()
    fun insert(item: NetworkLoad) = local.insert(item)
}