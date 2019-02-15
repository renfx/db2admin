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
                    if(data && data.success){
                        callBack(data.map)
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