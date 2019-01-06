<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">

    $(function () {

        var toolbar = [{
            iconCls: 'icon-edit',
            text: "专辑详情",
            handler: function () {
                var row = $("#album").treegrid("getSelected");
                if (row != null && row.id % 1 === 0) {

                    $('#AlbumDetailDialog').dialog({
                        title: '专辑详情',
                        width: 500,
                        height: 200,
                        closed: false,
                        cache: false,
                        modal: true
                    });
                    $('#albumDetail').edatagrid({
                        url: '${pageContext.request.contextPath}/album/queryOne?id=' + row.id,
                        columns: [[
                            {field: 'title', title: '名字', width: 100},
                            {field: 'count', title: '章节数', width: 100},
                            {field: 'score', title: '评分', width: 100},
                            {field: 'author', title: '作者', width: 100},
                            {field: 'broadcast', title: '播音员', width: 100},
                            {field: 'pubDate', title: '上传日期', width: 100}
                        ]],
                        fitColumns: true,
                        fit: true,
                        view: detailview,
                        detailFormatter: function (rowIndex, rowData) {
                            return '<table><tr>' +
                                '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/datagrid/img/' + rowData.coverImg + '" style="height:50px;"></td>' +
                                '<td style="border:0">' +
                                '<p>专辑简介: ' + rowData.brief + '</p>' +
                                '<p>上传日期: ' + rowData.pubDate + '</p>' +
                                '</td>' +
                                '</tr></table>';
                        },
                    });
                } else {
                    $.messager.alert("警告", "请先选中专辑!");
                }
            }
        }, '-', {
            text: "添加专辑",
            iconCls: 'icon-add',
            handler: function () {
                $('#AA').dialog({
                    title: '添加专辑',
                    width: 400,
                    height: 300,
                    closed: false,
                    cache: false,
                    modal: true
                });
            }
        }, '-', {
            text: "添加音频",
            iconCls: 'icon-add',
            handler: function () {
                var row = $("#album").treegrid("getSelected");

                if (row != null && row.id % 1 === 0) {
                    albumId = row.id;
                    $('#addChapter').dialog({
                        title: '添加章节',
                        width: 400,
                        height: 300,
                        closed: false,
                        cache: false,
                        modal: true
                    });
                } else {
                    alert("请先选中专辑!");
                }
            }
        }, '-', {
            text: "音频下载",
            iconCls: 'icon-save',
            handler: function () {
                var row = $("#album").treegrid("getSelected")
                if (row != null && row.id % 1 != 0) {
                    location.href = "${pageContext.request.contextPath}/chapter/down?fileName=" + row.url;
                } else {
                    alert("请先选中要下载的章节!");
                }
            }
        }, '-', {
            text: "导入",
            iconCls: 'icon-add',
            handler: function () {
                location.href = "${pageContext.request.contextPath}"
            }
        }, '-', {
            text: "导出",
            iconCls: 'icon-add',
            handler: function () {
                location.href = "${pageContext.request.contextPath}/album/export"
            }
        }]


        $('#album').treegrid({
            method: "get",
            url: '${pageContext.request.contextPath}/album/queryAll',
            idField: 'id',
            treeField: 'title',
            columns: [[
                {field: 'title', title: '名字', width: 60},
                {field: 'duration', title: '时长', width: 80},
                {field: 'size', title: '大小', width: 80},
                {field: 'url', title: '下载地址', width: 80, formatter: playAudio},
                {field: 'uploadDate', title: '上传时间', width: 80}
            ]],
            fit: true,
            fitColumns: true,
            toolbar: toolbar,
            pageList: [1, 3, 5, 7, 9],
            pageSize: 3,
            pagination: true
        });
    })

    //双击播放音频
    function playAudio(value, row, index) {
        // console.log(row);
        if (row.url != null) {
            return "<audio id='player' controls='controls' src='${pageContext.request.contextPath}/datagrid/music/" + row.url + "'> </audio>";

        }
    }

    function addAlbum() {
        $("#addAlbumForm").form("submit", {
            url: "${pageContext.request.contextPath}/album/addAlbum",
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function () {
                $("#AA").dialog("close");
                $("#album").datagrid("load");
                $.messager.show({
                    title: "提示信息",
                    msg: "添加成功！"
                });

            }
        });
    }

    function addChapter() {
        $("#addChapterForm").form("submit", {
            url: "${pageContext.request.contextPath}/chapter/addChapter?albumId=" + albumId,
            onSubmit: function () {
                return $(this).form("validate");
            },
            success: function () {
                $("#addChapter").dialog("close");
                $("#album").datagrid("load");
                $.messager.show({
                    title: "提示信息",
                    msg: "添加成功！"
                });

            }
        })
    }

</script>

<table id="album">

</table>

<%--专辑详情--%>
<div id="AlbumDetailDialog">
    <div id="albumDetail"></div>
</div>


<%--添加专辑--%>
<div id="AA">
    <form method="get" id="addAlbumForm" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="1" align="center" style="font-size: 17px">
            <tr>
                <td>名字:</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>集数:</td>
                <td><input type="text" name="count"></td>
            </tr>
            <tr>
                <td>图片:</td>
                <td><input type="file" name="coverImg"></td>
            </tr>
            <tr>
                <td>评分:</td>
                <td><input type="text" name="score" placeholder="1~10"></td>
            </tr>
            <tr>
                <td>作者:</td>
                <td><input type="text" name="author"></td>
            </tr>
            <tr>
                <td>播音员:</td>
                <td><input type="text" name="broadcast"></td>
            </tr>
            <tr>
                <td>简介:</td>
                <td><input name="brief"></td>
            </tr>
        </table>
        <br/>
        <div align="center"><input type="button" value="添加" onclick="addAlbum()">&nbsp;&nbsp;<input type="reset"
                                                                                                    value="重置"></div>
    </form>
</div>


<%--添加音频--%>
<div id="addChapter">
    <form method="post" id="addChapterForm" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" border="1" align="center" style="font-size: 17px">
            <tr>
                <td>音频名称:</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>音频文件:</td>
                <td><input type="file" name="music"></td>
            </tr>
        </table>
        <br/>
        <div align="center"><input type="button" value="添加" onclick="addChapter()">&nbsp;&nbsp;<input type="reset"
                                                                                                      value="重置"></div>
    </form>
</div>