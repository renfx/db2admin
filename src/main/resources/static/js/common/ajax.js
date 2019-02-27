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
                        let html = "<div class=\"layui-content\">\n" +
                            "    <div class=\"layui-row\">\n" +
                            "        <div class=\"layui-card\">" +
                            "    <ul class=\"layui-timeline\">\n";
                        $.each(data.stackTrace,(key,value)=>{
                            html+="  <li class=\"layui-timeline-item\" style='margin-bottom: 0px'>\n" +
                                "    <i class=\"layui-icon layui-timeline-axis\">&#xe63f;</i>\n" +
                                "    <div class=\"layui-timeline-content layui-text\">\n" +
                                "      <h4 class=\"layui-timeline-title\">类:"+value.className+" 方法名:"+value.methodName+
                                " 行:" +value.lineNumber+
                                "      </h4>\n" +
                                "    </div>\n" +
                                "  </li>\n";
                        })
                        html+="</ul>" +
                            "</div>\n" +
                            "    </div>\n" +
                            "</div>";
                        parentLayer.open({
                            type: 1,
                            title:"异常信息",
                            area: ['100%', '100%'], //宽高
                            content: html
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