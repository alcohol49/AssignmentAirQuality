# 串接政府公開 API，呈現空污資料

- threshold 設定為 20。30 的話上下資料不太平均。

# Data
- Fetch with Retrofit and Gson converter
- 資料在 app lifecycle 內僅抓一次。藉由 repository 保存 in-memory cache。
- 透過 AndroidViewModel 取得 Application 避免 context leak & 後續擴充 context 相關的資料 (e.g. database)

# UI
## MainFragment
- 兩個 RecyclerView 各自獨立，擁有各自的 adapter 與 view holder
- 由 ViewModel 抓取資料並產生各自的 list data
- Layout 的需求可透過 Constraint Layout 的特性達成
## SearchFragment
- 用 EditText 的 onTextChange 監聽輸入資料，透過 switchMap 來處理字串的比對。當 input 改變時也立刻改變結果。
- 用相同的 ViewHolder 

![](device-2022-01-02-140125.png)
![](device-2022-01-02-140154.png)
