layui.use(['http','layer'], function(){
    var $ = layui.$, http = layui.http;


    http.ajaxPost("/baseData/get",{},function(data){
        $.each(data.baseData.tableMap,function(key,value){
            $("#hideDl").after('<dl class="layui-nav-child"><dd><a href="/admin/view/'+key+'.html">'+value.table_comment+'</a></dd></dl>');
        })

    });
});
