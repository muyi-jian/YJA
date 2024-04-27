import request from "@/utils/request.js";

export const articleCategoryService = ()=> {
    return request.get('/category')
}
//文章分类添加
export const articleCategoryAddService = (categoryData)=>{
    return request.post('/category',categoryData)
}
//修改分类
export const articleCategoryUpdateService = (categoryData)=>{
    return request.put('/category',categoryData)
}

//删除分类
export const articleCategoryDeleteService = (id) => {
    return request.delete('/category/'+id)
}

//文章列表查询
export const articleListService = (params) => {
    return request.get('/article', { params: params })
}

//添加文章
export const articleAddService = (articleModel)=>{
    return request.post('/article',articleModel)
}