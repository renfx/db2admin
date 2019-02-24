//layui模块的定义
layui.define(['layer'],function(exports){
    var layer = layui.layer
        ,$ = layui.$;

    var obj = {
        ajaxPost: function(url,data,callBack){
            var dataMap={};
            $.ajax({
                type: "POST",
                url: url,
                data: data,
                dataType: "json",
                success: function(data){
                    console.log("ajaxPost",data)
                    if(data && data.success){
                        callBack(data.map)
                    }else{
                        layer.msg(data.msg)
                    }
                },
                error:function(){
                    layer.msg("网络异常")
                }
            });
            return dataMap;
        }
    };

    exports('http', obj);
});