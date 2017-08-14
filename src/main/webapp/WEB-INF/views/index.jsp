<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>首页，蜘蛛人</title>
    <meta charset="UTF-8">
    <script src="/source/js/jquery-1.11.0.min.js"></script>
    <script src="/source/layer/layer.js"></script>
</head>
<body>
<h2>Welcome,crawler!</h2>
<button class="btn new">新建</button>
</body>
<script>
    $(function () {
        getHeader();
        $(".btn new").click(function () {
            layer.alert("哈哈哈");
        });
        function getHeader() {
            $.ajax({
                url: '/header/headerList',
                type: 'POST',
                success: function (data) {
                    if (data.seccess) {
                        $.each(data.data)(function (index) {
                            alert("data");
                        });
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
