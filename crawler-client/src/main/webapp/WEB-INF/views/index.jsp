<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页，蜘蛛人</title>
    <meta charset="UTF-8">
    <link href="">
    <script src="/source/js/jquery-1.11.0.min.js"></script>
    <script src="/source/layer/layer.js"></script>
</head>
<body>
<h2>Welcome,crawler!</h2>
<div>
    <table>
        <thead class="table table-hover table-bordered table-striped">
        <td>id</td>
        <td>任务名称</td>
        <td>操作</td>
        </thead>
        <tbody id="tb">
        <tr>
            <td>加载中...</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
<script>
    $(function () {
//        $("#start111").click(function () {
//            var url = $(this).val();
//            layer.alert("正在开始。。。")
////            $.ajax({
////                url: url,
////                success: function () {
////                    layer.alert("正在开始。。。")
////                },
////                error: function () {
////                    layer.alert("服务器未响应")
////                }
////            })
//        });
        getHeader();
        $(".btn new").click(function () {
            layer.alert("哈哈哈");
        });
        function getHeader() {
            $.ajax({
                url: '/job/jobList.crawl',
                type: 'POST',
                success: function (data) {
                    if (data.success) {
                        var str = "";
                        $(data.data.jobList).each(function (index, ele) {
                            str += '<tr>' +
                                '<td>' + ele["id"] + '</td>' +
                                '<td>' + ele["name"] + '</td>' +
                                '<td><a class="start111" href="/job/startJob.crawl?jobId=' + ele["id"] + '">开始</a></td>' +
                                +'</td>';
                        });
                        $("#tb").html(str);
                    } else {
                        layer.alert(data.msg)
                    }
                },
                error: function () {
                    layer.alert("服务器未响应")
                }
            });
        }

    })
</script>
</html>
