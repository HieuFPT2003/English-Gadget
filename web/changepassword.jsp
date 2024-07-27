<%-- 
    Document   : changepassword
    Created on : Jun 20, 2024, 11:56:15 PM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="icon" href="images/logoTab-01.png" type="images/x-icon">
        <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
        <title>English Gadget</title>
    </head>
    <body>
        
       <main id="content" role="main" class="w-full  max-w-md mx-auto p-6">
    <div class="mt-7 bg-white  rounded-xl shadow-lg dark:bg-gray-800 dark:border-gray-700 border-2 border-indigo-300">
      <div class="p-4 sm:p-7">
        <div class="text-center">
          <h1 class="block text-2xl font-bold text-gray-800 dark:text-white">Change Password</h1>
          <p class="mt-2 text-sm text-gray-600 dark:text-gray-400">
           Enter your new password below
            
          </p>
        </div>
                   <%
          HttpSession currentSession = request.getSession();
          String email = (String) currentSession.getAttribute("email");
        %>
        <div class="mt-5">
            <form action="resetPass" method="post">
         
            <div class="grid gap-y-4">
              <div>
                  <label for="email" class="block text-sm font-bold ml-1 mb-2 dark:text-white">Email</label>
                <div class="relative">
                    <input type="email" id="email" name="email" class="py-3 px-4 block w-full border-2 border-gray-200 rounded-md text-sm focus:border-blue-500 focus:ring-blue-500 shadow-sm" rpassword1equired aria-describedby="email-error" value="<%= email != null ? email : "" %>">
                </div>

                <label for="password" class="block text-sm font-bold ml-1 mb-2 dark:text-white">New Password</label>
                <div class="relative">
                    <input type="password" id="password" name="password" class="py-3 px-4 block w-full border-2 border-gray-200 rounded-md text-sm focus:border-blue-500 focus:ring-blue-500 shadow-sm" rpassword1equired aria-describedby="email-error">
                </div>
                 <label for="comfirmpass" class="block text-sm font-bold ml-1 mb-2 dark:text-white">Comfirm Password</label>
                <div class="relative">
                    <input type="password" id="comfirmpass" name="comfirmpass" class="py-3 px-4 block w-full border-2 border-gray-200 rounded-md text-sm focus:border-blue-500 focus:ring-blue-500 shadow-sm" required aria-describedby="email-error">
                </div>
                <p class="hidden text-xs text-red-600 mt-2" id="email-error">Please include a valid email address so we can get back to you</p>
              </div>
              <button type="submit" class="py-3 px-4 inline-flex justify-center items-center gap-2 rounded-md border border-transparent font-semibold bg-blue-500 text-white hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-all text-sm dark:focus:ring-offset-gray-800">Reset password</button>
              <h1 style="color:red;text-align: center">${logmessage}</h1>
              <h1 style="color:red;text-align: center">${logmess}</h1>
              <h1 style="color:red;text-align: center">${mess}</h1>
            </div>
          </form>
        </div>
      </div>
    </div> 
    
  </main> 
    </body>
</html>
