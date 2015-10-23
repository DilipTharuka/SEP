<%-- 
    Document   : index
    Created on : Jul 1, 2015, 6:23:31 PM
    Author     : Dilip
--%>


<%@page import="controller.TimelineController"%>
<%@page import="model.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Date" %>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns:h="http://xmlns.jcp.org/jsf/html" xmlns:f="http://xmlns.jcp.org/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="timeline.css" rel="stylesheet" type="text/css">
        <script src="jquery/jquery-1.11.2.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="timeline.js"></script>
        <title>News Management System</title>

        <%

            ArrayList<News> newsList = TimelineController.getApprovedNewsList();
            pageContext.setAttribute("newsList", newsList);
        %>

    </head>

    <body>
        <!-------------------------------------- News Timeline ------------------------------------------->
        <div class="container">

            <div class="page-header center-block">
                <img class="img-responsive" src="images/header.jpg" />
            </div>
            <ul class="timeline">
                <c:forEach var="news" items= "${pageScope.newsList}" varStatus="count">
                    <c:if test="${count.index%2==0}">
                        <li>
                            <div class="timeline-badge primary">
                                <a><i class="glyphicon glyphicon-record" rel="tooltip" title="<c:out value="${news.getPublishedDateTime()}"/>" id=""></i></a>
                            </div>
                            <div class="timeline-panel">
                                <div class="timeline-footer">
                                    <h4><c:out value="${news.getTitle()}"/></h4>
                                </div>
                                <c:if test="${news.getImageList().size()!=0}">
                                    <div class="timeline-heading">
                                        <img class="img-responsive" src="ftp/post/image/${news.getImageList().get(0)}" />
                                    </div>
                                </c:if>
                                <div class="timeline-body">
                                    <p><c:out value="${news.getDescription().substring(0,300)} .... "/></p>
                                </div>

                                <div class="timeline-footer">
                                    <a><span><i class="glyphicon glyphicon-time" ></i> ${news.getPublishedDateTime()}</span></a>
                                    <a class="pull-right" href="#newsModal${count.index}" data-toggle="modal">View more</a>
                                </div>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${count.index%2==1}">
                        <li  class="timeline-inverted">
                            <div class="timeline-badge primary"><a><i class="glyphicon glyphicon-record invert" rel="tooltip" title="<c:out value="${news.getPublishedDateTime()}"/>" id=""></i></a></div>
                            <div class="timeline-panel">
                                <div class="timeline-footer">
                                    <h4><c:out value="${news.getTitle()}"/></h4>
                                </div>
                                <c:if test="${news.getImageList().size()!=0}">
                                    <div class="timeline-heading">
                                        <img class="img-responsive" src="ftp/post/image/${news.getImageList().get(0)}" />
                                    </div>
                                </c:if>
                                <div class="timeline-body">
                                    <p><c:out value="${news.getDescription().substring(0,300)} .... "/></p>
                                </div>

                                <div class="timeline-footer">
                                    <a><span><i class="glyphicon glyphicon-time" ></i> ${news.getPublishedDateTime()}</span></a>
                                    <a class="pull-right" href="#newsModal${count.index}" data-toggle="modal">View more</a>
                                </div>
                            </div>
                        </li>
                    </c:if>
                </c:forEach>         
                <li class="clearfix" style="float: none;"></li>
            </ul>
        </div>    

        <!----------------------------------- Modals ---------------------------------------------->


        <c:forEach var="news" items= "${newsList}" varStatus="newsCount">
            <div class="modal fade" id="newsModal${newsCount.index}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h3 class="modal-title text-center" id="myModalLabel">${news.getTitle()}</h3>
                        </div>
                        <div class="modal-body">
                            <p>${news.getDescription()}</p>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-12" id="carousel-bounding-box">
                                <div id="myCarousel${newsCount.index}" class="carousel slide">
                                    <!-- Carousel items -->
                                    <div class="carousel-inner">
                                        <c:forEach var="image" items= "${news.getImageList()}" varStatus="imageCount">
                                            <c:if test= "${imageCount.index==0}">
                                                <div class="active item" data-slide-number="${imageCount.index}">
                                                    <img class="img-rounded img-responsive center-block" src="ftp/post/image/${image}">
                                                </div>
                                            </c:if>
                                            <c:if test= "${imageCount.index!=0}">
                                                <div class="item" data-slide-number="${imageCount.index}">
                                                    <img class="img-rounded img-responsive center-block" src="ftp/post/image/${image}">
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                </div>
                                <ul class="carousel-controls-mini list-inline text-center">
                                    <li><a href="#myCarousel${newsCount.index}" data-slide="prev">‹</a></li>
                                    <li><a href="#myCarousel${newsCount.index}" data-slide="next">›</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class = "modal-footer">
                            <center>
                                <div class= "col-md-12">
                                    <c:forEach var="video" items= "${news.getVideoList()}" varStatus="videoCount">
                                        <div class="embed-responsive embed-responsive-4by3 modal-video">
                                            <iframe class="embed-responsive-item center-block" src=${video}></iframe>
                                        </div> 
                                    </c:forEach> 
                                </div>
                            </center>
                        </div>
                        <div class="modal-footer">
                            <div class= "col-md-12">
                                <span><i class="glyphicon glyphicon-calendar"></i> ${news.getPublishedDate()}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <c:forEach var="news" items= "${newsList}" varStatus="newsCount">
            <script>
                $('#myCarousel${newsCount.index}').carousel({
                    interval: 4000
                });
            </script>
        </c:forEach>
        <div class="container">
            <hr>
            <div class = "raw">

                <div class = "col-md-4">
                    <button class="btn btn-primary center-block" href="#approverLogin" data-toggle="modal" >Approver Login</button>
                </div>
                <div class="text-center center-block col-md-4"> 
                    <a href=""><i id="social" class="fa fa-facebook-square fa-3x social-fb"> </i></a>
                    <a href=""><i id="social" class="fa fa-twitter-square fa-3x social-tw"> </i></a>
                    <a href=""><i id="social" class="fa fa-google-plus-square fa-3x social-gp"> </i></a>
                    <a href=""><i id="social" class="fa fa-envelope-square fa-3x social-em"> </i></a>
                    <br />
                    <p class="txt-railway"> PegasusNews.com © Pegasus 2015 </p>  
                </div>

                <div class="text-center col-md-4 center-block">
                    <form name="mobile_form" method="GET" action="FormController" id="mobile_form">
                        <div class="input-group col-md-8 col-md-offset-2">
                            <input type="text" placeholder="mobile number" name="mobile"  class="form-control">
                            <span class="input-group-btn">
                                <button class="btn btn-primary" type="sumbit" >Subscribe</button>
                            </span>
                        </div>
                        <div class="col-md-8 col-md-offset-2">
                            </br>
                            <div id = "alert_placeholder"></div>
                            </br>
                        </div>  
                    </form>
                </div>
            </div>

            <div class="modal fade" id="approverLogin" tabindex="-1" role="dialog">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h3 class="modal-title text-center" id="myModalLabel">Approver Login</h3>
                        </div>
                        <div class="modal-body">
                            <form role="form" action="approver.jsp" method="post" id="login-form" autocomplete="off">
                                <div class="form-group">
                                    <label class="sr-only">Username</label>
                                    <input type="text" name="username" class="form-control" placeholder="username">
                                </div>
                                <div class="form-group">
                                    <label class="sr-only">Password</label>
                                    <input type="password" name="password" class="form-control" placeholder="Password">
                                </div>
                                <input type="submit" class="btn btn-primary btn-lg btn-block" value="Log in">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>                                                                
</html>
<script type="text/javascript">
    var form = $('#mobile_form');
    form.submit(function () {

        $.ajax({
            type: form.attr('method'),
            url: form.attr('action'),
            data: form.serialize(),
            success: function (data) {
                var result = data;
                //alert(result);
                if (result == 1)
                    $('#alert_placeholder').html('<div class="alert alert-success"><a class="close" data-dismiss="alert">×</a><span><strong>Sucess!</strong>  You will be notified soon</span></div>');
                else
                    $('#alert_placeholder').html('<div class="alert alert-danger"><a class="close" data-dismiss="alert">×</a><span><strong>Failed!</strong>  Check your connection and try again </span></div>');
            }
        });
        return false;
    });
</script>
