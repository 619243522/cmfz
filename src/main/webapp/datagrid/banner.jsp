<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
    $(function () {
        var toolbar = [{
            iconCls: 'icon-add',
            text: "添加",
            handler: function () {
                $('#dd').dialog({
                    title: '添加轮播图',
                    width: 400,
                    height: 250,
                    closed: false,
                    cache: false,
                    modal: true
                });
            }
        }, '-', {
            text: "修改",
            iconCls: 'icon-edit',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("editRow", index);
                } else {
                    alert("请先选中行")
                }


            }
        }, '-', {
            text: "删除",
            iconCls: 'icon-remove',
            handler: function () {
                //获取选中行
                var row = $("#dg").edatagrid("getSelected");
                if (row != null) {
                    //编辑指定行
                    var index = $("#dg").edatagrid("getRowIndex", row);
                    $("#dg").edatagrid("destroyRow");
                } else {
                    alert("请先选中行")
                }
            }
        }, '-', {
            text: "保存",
            iconCls: 'icon-save',
            handler: function () {
                $("#dg").edatagrid("saveRow")
            }
        }]

        $('#dg').edatagrid({
            destroyUrl: "${pageContext.request.contextPath}/banner/deleteBanner",
            updateUrl: "${pageContext.request.contextPath}/banner/updateBanner",
            url: '${pageContext.request.contextPath}/banner/queryByPage',
            columns: [[
                {field: 'title', title: '名称', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {required: true}
                    }
                },
                {field: 'pubDate', title: '时间', width: 100, align: 'right'}
            ]],
            fitColumns: true,
            fit: true,
            pagination: true,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            toolbar: toolbar,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/datagrid/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>描述: ' + rowData.discription + '</p>' +
                    '<p>日期: ' + rowData.pubDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

    function addBanner() {
        $("#addForm").form("submit", {
            url: "${pageContext.request.contextPath}/banner/insertBanner",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function () {
                $("#dd").dialog("close");
                $.messager.show({
                    title: "提示信息",
                    msg: "添加成功！"
                });
                $("#dg").datagrid("load");
            }
        });
    }

</script>

<table id="dg"></table>

<%--添加轮播图--%>
<div id="dd">
    <form method="post" id="addForm" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="1" align="center">
            <tr>
                <td>名称:</td>
                <td><input name="title"></td>
            </tr>
            <tr>
                <td>图片:</td>
                <td><input type="file" name="img"></td>
            </tr>
            <tr>
                <td>状态:</td>
                <td><input type="radio" name="status" value="y">显示
                    <input type="radio" name="status" value="n">不显示
                </td>
            </tr>
            <tr>
                <td>上传日期:</td>
                <td><input name="pubDate" class="easyui-datebox" required="required" editable="false"></td>
            </tr>
            <tr>
                <td>描述:</td>
                <td><input name="discription"></td>
            </tr>
        </table>
        <br/>
        <div align="center"><input type="button" value="添加" onclick="addBanner()">&nbsp;&nbsp;<input type="reset"
                                                                                                     value="重置"></div>
    </form>
</div>