<%-- 
    Document   : navbarUser
    Created on : May 21, 2024, 9:23:53 PM
    Author     : Q.Hieu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <style>
              .nav{
                display: flex;
                justify-content: space-between;
                padding: 4px;
                background-color: #198754;
              }
              .headerIconBtn{
                font-size: 24px;
                padding:0 2px 0 4px;
                margin-right: 12px;
                border: none;
                background-color: transparent;
              }
              .headerMiddle{
                position: relative;
              }
              .midlleIcon{
                font-size: 20px;
                padding:0 2px 0 4px;
                margin-right: 12px;
                border: none;
                background-color: transparent;
                position: absolute;
                top: 0;
                right: 1%;
              }
              .searchInput{
                width: 320px;
              }
            .headerRight{
                display: flex;
                align-items: center;
                position: relative;
            }   
            .rightBtn{
                
                border: none;
                background-color: transparent;
                text-align: center;
                align-items: center;
                font-size: 20px;
                padding: 6px 8px;
                border-radius: 20px;
                transition: all 0.2s ease-in-out;
            }
            .rightIcon{
                padding-right: 8px;
                font-size: 24px;
            }
            .titleCreate{
                position: absolute;
                top:20%
            }
            .rightBtn:hover{
                background-color: white;
                color: black;
            }
            .listUser{
                margin-right:50px;
                border: none;
                background-color: transparent;
            }
        </style>
    </head>
    <body>
         <nav class="nav navbar navbar-expand-lg bg-body-tertiarys container-fluid">
            <div class="headerLeft d-flex">
                <div class="leftBar leftBarIcon"> 
                    <button class="headerIconBtn" type="button" data-bs-toggle="offcanvas" data-bs-target="#leftNavbar" aria-controls="staticBackdrop">
                        <i class="bi bi-list"></i>
                    </button>
                </div>
                <div class="logo fs-4">English Gadget</div>
            </div> 
            <div class="headerMiddle d-flex">
                <input class="form-control form-control-sm me-2 searchInput" type="search" placeholder="Search Blog" aria-label="Search" >
                <i class="bi bi-search midlleIcon"></i>
            </div>
            <div class="headerRight">
                <button type="button" class="rightBtn" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    <i class="bi bi-plus-lg"></i>
                    Create
                </button>
                <div class="dropdown-center">
                    <button class="dropdown-toggle listUser" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-person-circle rightIcon"></i>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Profile</a></li>
                        <li><a class="dropdown-item" href="#">My Blog</a></li>
                        <li><a class="dropdown-item" href="#">Log 0ut</a></li>
                      </ul>
                  </div>
            </div>
        </nav>

    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form class="modal-content" action="CreatePost" method="post">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Create a Post</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <textarea style="width: 100%; height: 300px;" placeholder="What are you thinking?" name="contentpost" id="contentpost"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Post</button>
                </div>
            </form>
        </div>
    </div>

    <div class="offcanvas offcanvas-start" data-bs-backdrop="static" tabindex="-1" id="leftNavbar" aria-labelledby="staticLeftBackdropLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="staticLeftBackdropLabel">Offcanvas</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <div>
            I will not close if you click outside of me.
          </div>
        </div>
      </div>
        
        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>
