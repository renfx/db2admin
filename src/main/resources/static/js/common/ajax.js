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
                        let parentLayer = layer;
                        if(parent && parent.layer){
                            parentLayer = parent.layer;
                        }
                        let html ="<textarea readonly style='height:100%;resize:none;' class=\"layui-textarea\">"+data.exception+"</textarea>\n";
                        parentLayer.open({
                            type: 1,
                            title:"异常信息",
                            area: ['95%', '95%'], //宽高
                            content: html,
                            success: function(layero, index){
                                parentLayer.msg("后台异常,已经弹出异常信息");
                            }
                        });
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