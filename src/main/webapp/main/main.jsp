<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/icon.css"></link>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/themes/IconExtension.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/china.js"></script>

    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath }/menu/showMenu",
                type: "get",
                dataType: "json",
                success: function (data) {
                    $.each(data, function (index, first) {
                        var title = first.title;
                        var menu = "";
                        $.each(first.list, function (index, second) {
                            menu += "<p style='text-align:center'><a href='javascript:void(0)' class='easyui-linkbutton' data-options=\"iconcls:'" + second.iconcls + "'\" onclick=\"getSelect('" + second.title + "','" + second.iconCls + "','" + second.url + "')\">" + second.title + "</a></p>";
                        });
                        $('#aa').accordion('add', {
                            title: title,
                            content: menu,
                            seslected: false,
                        });
                    });
                },
            });
        });

        function getSelect(title, iconCls, url) {
            var isok = $('#tt').tabs('exists', title);
            if (isok) {
                $('#tt').tabs('select', title);
            } else {
                $('#tt').tabs('add', {
                    title: title,
                    closable: true,
                    iconCls: "",
                    selected: true,
                    href: "${pageContext.request.contextPath }/datagrid" + url,
                });
            }
        }

        /*$(function() {
            $.ajax({
                type : 'POST',
                dataType : "json",
                url : '/menu/showMenu',
                success : function(data) {
                    $.each(data, function(i, n) {//加载父类节点即一级菜单
                        if (i == 0) {//显示第一个一级菜单下的二级菜单
                            $('#aa').accordion('add', {
                                title : n.title,
                                iconCls : n.iconCls,
                                selected : true,
                                content : '<div style="padding:10px"><ul name="'+n.id+'"></ul></div>',
                            });
                        } else {
                            $('#aa').accordion('add', {
                                title : n.title,
                                iconCls : n.iconCls,
                                selected : false,
                                content : '<div style="padding:10px"><ul name="'+n.id+'"></ul></div>',
                            });
                        }

                    });
                }
            });
            //异步加载子节点，即二级菜单
            $('#aa').accordion({
                onSelect : function(title, index) {
                    $("ul[name='" + title + "']").tree({
                        url : '/menu/showMenu',
                        queryParams : {
                            text : title
                        },
                        animate : true,
                        lines : true,//显示虚线效果  
                        onClick: function(node){// 在用户点击一个子节点即二级菜单时触发addTab()方法,用于添加tabs
                            if(node.url){//判断url是否存在，存在则创建tabs
                                addTab(node);
                            }
                        }
                    });
                }
            });
        });*/
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.name} &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
            href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>