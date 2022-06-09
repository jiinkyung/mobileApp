package com.example.myapp

data class Row(val SH_NAME: String, val SH_PHONE: String, val SH_ADDR: String)
data class ListPriceModelStoreService(val row: MutableList<Row>)
data class ItemModel(val ListPriceModelStoreService: ListPriceModelStoreService)

// "ListPriceModelStoreService":{
// "list_total_count":1055,
// "RESULT":{"CODE":"INFO-000","MESSAGE":"정상 처리되었습니다"},
// "row":[{"SH_ID":"00000263","SH_NAME":"장미미용실","INDUTY_CODE_SE":"005","INDUTY_CODE_SE_NAME
//"INDUTY_CODE_SE_NAME":"이 미용업",
// "SH_ADDR":"서울특별시 중랑구 봉화산로56길 123, 중앙하이츠상가 1층",
// "SH_PHONE":"",
// "SH_WAY":"신내1동 신내초등학교 후문 쪽",
// "SH_INFO":"영업시간 :09시30분 - 22시