<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加页面</title>
<!--引入easyui的支持  -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
/**********初始化*******************************/
$(function(){
	$('#win').window('close');  // close a window  
	$('#ffemp').hide(); //员工页面的隐藏；
	//获取从后台获取的值，
	$.getJSON("doinit_Emp.do",function(map){
		var lswf=map.lswf;
		var lsdep=map.lsdep;
		//处理复选框
		for (var i = 0; i < lswf.length; i++) {
			var wf=lswf[i];
			$("#wf").append("<input type='checkbox' name='wids' id='wids' value='"+wf.wid+"'/>"+wf.wname)
			
		}
		//处理下拉列表
		$('#cc').combobox({  
			//获取数据的路径
		    data:lsdep,
		    //往后台传递值的名称
		    valueField:'depid',
		  //前台展示值的名称
		    textField:'depname', 
		    //默认显示的类容
		    value:3,
		    //下拉面板高度。
		    panelHeight:88
		}); 
	});
	
});
/**********初始化结束*******************************/
/**********员工结束*******************************/ 
  $(function(){
	 $('#dg1').datagrid({    
		    url:'findAllPage_Emp.do',
		    pagination:true,
		    pageNumber:5,
		    pageSize:1,
		    pageList:[5,10,15,20,25],
		    striped:true,
		    columns:[[    
		        {field:'eid',title:'编号',width:50,align:"center"}, 
		        {field:'ename',title:'姓名',width:50,align:"center"}, 
		        {field:'sex',title:'性别',width:50,align:"center"}, 
		        {field:'address',title:'地址',width:100,align:"center"}, 
		        {field:'sdate',title:'生日',width:100,align:"center"}, 
		        {field:'photo',title:'照片',width:100,align:'center',
		        	formatter: function(value,row,index){
		        		return '<img src=uppic/'+row.photo+' width="100" height="60" />'
		        	}	
		        },
		        
		        {field:'depname',title:'部门',width:50,align:"center"}, 
		        {field:'caozuo',title:'操作',width:200,align:"center",
		        	formatter: function(value,row,index){
		        		var bt1="<input type='button' onclick=delById("+row.eid+") value='删除'/>"
		        		var bt2="<input type='button' onclick=findById("+row.eid+") value='编辑'/>"
		        		var bt3="<input type='button' onclick=editById("+row.eid+") value='详情'/>"
		        		return bt1+bt2+bt3;
		        	}		 
		        },
		         
		    ]]    
		});  

 });
 
 
 
/**********员工结束*******************************/
 /**********查询单个 删除 以及详情*******************************/
 //删除
  function delById(eid){
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		    	//获取从后台获取的值，
		    	$.getJSON("delById_Emp.do?eid="+eid,function(code){
		    		if(code=='1'){
						$.messager.alert('提示','删除成功!!!');
						$('#dg1').datagrid('reload');    // 重新载入当前页面数据
					}else{
						$.messager.alert('提示','删除失败!!!');
					}
		    	});   
		    }    
		});  

}
 // 编急
   function findById(eid){
	   alert("确认编辑");
		 $('#hsdg').hide();		//隐藏展示列表
		 $('#hsp').hide();		//隐藏顶部P标签
		 $('#ffemp').show();  //员工管理页面显示 
		 $('#btupdate').show();//修改按钮显示
		 $('#btsave').hide();//保存按钮隐藏
	  $.getJSON("findById_Emp.do?eid="+eid,function(oldemp){
		  //取消复选框中选中的类容
		  $(":checkbox[name='wids']").each(function(){
			  $(this).prop("checked",false);
		  });
		  $('#ffemp').form('load',{
				'eid':oldemp.eid,
				'ename':oldemp.ename,
				'sex':oldemp.sex,
				'address':oldemp.address,
				'sdate':oldemp.sdate,
				'depid':oldemp.depid,
				'emoney':oldemp.emoney,
			});
		  //处理图片
		   $("#myphoto").attr('src','uppic/'+oldemp.photo);
		   //处理复选框
           var wids=oldemp.wids;
           $(":checkbox[name='wids']").each(function(){
				for(var i=0;i<wids.length;i++){
					if($(this).val()==wids[i]){
						$(this).prop("checked",true);	
					}
				}
			});
		});
	} 
 //详情
 function editById(eid){
	alert("aaaaaaaaa");
	 $.getJSON("editById_Emp.do?eid="+eid,function(emp){
		 //赋值
		 $("#enametxt").html(emp.ename);
		 $("#sextxt").html(emp.sex);
		 $("#addresstxt").html(emp.address);
		 $("#sdatetxt").html(emp.sdate);
		 $("#phototxt").html(emp.photo);
		 $("#deptxt").html(emp.depname);
		 $("#emoneytxt").html(emp.emoney);
		 $("#eidtxt").html(emp.eid);
		 //获取福利
		  var lswf=emp.lswf;
		 var wnames=[];//福利名称数组
		 for(var i=0;i<lswf.length;i++){
			 var wf=lswf[i];
			 wnames.push(wf.wname);
		 }
		 var strwname=wnames.join(',');
		 $("#wftxt").html(strwname); 
		 $("#domyphoto").attr('src','uppic/'+emp.photo); 
		 $('#win').window('open');  // open a window 
	 });	
	}
  /**********查询单个 删除 以及详情结束*******************************/
/**********保存和修改*******************************/
//保存
 $(function(){
		$("#btsave").click(function(){
			$.messager.progress();	// 显示进度条
			$('#ffemp').form('submit', {
				url:'save_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','保存成功!!!');
						$('#dg1').datagrid('reload');    // 重新载入当前页面数据 
						$('#ffemp').hide();  //员工管理页面关闭 
						 $('#hsdg').show();		//显示展示列表
						 $('#hsp').show();		//显示顶部P标签

					}else{
						$.messager.alert('提示','保存失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});


		});
		//取消
			//取消
	$("#btreset").click(function(){
		
		window.open('empadd.jsp');
	});
  //修改
		$("#btupdate").click(function(){
			$('#ffemp').hide();  //员工管理页面隐藏
			$.messager.progress();	// 显示进度条
			$('#ffemp').form('submit', {
				url:'update_Emp.do',
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
					}
					return isValid;	// 返回false终止表单提交
				},
				//回调函数
				success: function(code){
					if(code=='1'){
						$.messager.alert('提示','修改成功!!!');
						$('#dg1').datagrid('reload');    // 重新载入当前页面数据
						 $('#hsdg').show();		//显示展示列表
						 $('#hsp').show();		//显示顶部P标签

					}else{
						$.messager.alert('提示','修改失败!!!');
					}
					$.messager.progress('close');	// 如果提交成功则隐藏进度条
				}
			});


		});
	});
  //部门添加
 
  //查询部门
  function find(depid){
	  $.getJSON("find_dep.do?depid="+depid,function(oldemp){
		  //取消复选框中选中的类容
		  $(":checkbox[name='wids']").each(function(){
			  $(this).prop("checked",false);
		  });
		  $('#ffemp').form('load',{
				'depid':oldemp.depid
				
			});
	  });
  }
 
  /*********保存结束********************************/
  /**************按钮隐藏*********************/
   function glbt(){
	 alert("确认添加？");
	 $('#ffemp').show();  //员工管理页面显示 
	 $('#btupdate').hide();//修改按钮隐藏
	  $('#hsdg').hide();		//隐藏展示列表
	 $('#hsp').hide();  	
	 $('#dsa').hide();
 }
   /**************按钮隐藏结束***********************/
   $(function(){
				$("#dsa").click(function(){
					$(window).attr('location', "dep.jsp");
				});
			});
 
</script>
</head>
<body>
<p id="hsp" align="center" style="color:red " >
<input type="button" id="glbt" name="glbt" value="员工列表添加" onclick=glbt()  />
<p id="dsa" align="center" style="color:red " >
<input type="button" id="dsav" name="dsav" value="部门列表" onclick=dsav()  /></p>
<p align="center">列表</p>
<div id="hsdg">
<hr />
<table id="dg1" ></table> 
<hr />
</div>
  
  <form action="" name="ffemp" id="ffemp" method="post" enctype="multipart/form-data">
    <table border="1px" width="500px" align="center">
       <tr bgcolor="#FFFFCC" align="center">
          <td colspan="3">员工管理表</td>
       </tr>
        <tr>
          <td>姓名：</td>
          <td><input type="text" id="ename" name="ename" class="easyui-validatebox" data-options="required:true"></td>
          <td rowspan="7" width="100" height="100">
            <a href="uppic/default.jpg" >
            <img id="myphoto" alt="图片不存在" src="uppic/default.jpg">
            </a>
          </td>
       </tr>
        <tr>
          <td>性别:</td>
          <td>
             <input type="radio" is="sex" name="sex" checked="checked" value="男">男
             <input type="radio" is="sex" name="sex"  value="女">女
             
          </td>
       </tr>
        <tr>
          <td>地址</td>
           <td><input type="text" id="address" name="address"></td>
       </tr>
        <tr>
          <td>生日</td>
          <td><input type="date" id="sdate" name="sdate"></td>
       </tr>
        <tr>
          <td>照片</td>
          <td><input type="file" id="pic" name="pic"></td>
       </tr>
        <tr>
          <td>部门</td>
          <td><input type="text" id="cc" name="depid"></td>
       </tr>
        <tr>
          <td>薪资</td>
          <td><input type="text" id="emoney" name="emoney" value="2000"> </td>
       </tr>
        <tr>
          <td>福利</td>
          <td colspan="2">
          <spann id="wf"></span></td>
       </tr>
        <tr bgcolor="#FFFFCC" align="center">
         <td colspan="3"> 
             <input type="hidden" id="eid" name="eid">
             <input type="button" id="btsave" name="btsave" value="保存">
             <input type="button" id="btupdate" name="btupdate" value="修改">
             <input type="reset" id="btreset" name="btreset" value="重置">
         </td>
       </tr>
    </table>
  </form>
  <div id="win" class="easyui-window" title="员工详情" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true">   
     <table border="1px" width="550px" align="center">
     <tr>
     <td width="100px">姓名</td>
     <td width="200px">
     <span id="enametxt"></span>
     </td>
     <td rowspan="7">
      <img id="domyphoto" alt="图片不存在" src="uppic/default.jpg" width="240px" height="260px">
     </td>
    </tr>
     <tr>
     <td>性别</td>
     <td>
      <span id="sextxt"></span>
     </td>
    </tr>
     <tr>
     <td>地址</td>
     <td>
     <span id="addresstxt"></span>
     </td>
     
    </tr>
     <tr>
     <td>生日</td>
     <td>
     <span id="sdatetxt"></span>
     </td>
     
    </tr>
     <tr>
     <td>照片</td>
     <td>
     <span id="phototxt"></span>
     </td>
     
    </tr>
     <tr>
     <td>部门</td>
     <td>
     <span id="deptxt"></span>
     </td>
    
    </tr>
     <tr>
     <td>薪资</td>
     <td>
     <span id="emoneytxt"></span>
     </td>
    </tr>
     <tr>
     <td>福利</td>
     <td colspan="2">
     <span id="wftxt"></span>
     </td>
    </tr>
    <tr >
     <td>编号</td>
     <td colspan="2">
     <span id="eidtxt"></span>
     </td>
    </tr>
  </table>
</div>  
  
</body>
</html>