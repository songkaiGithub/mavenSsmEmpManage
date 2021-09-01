<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>部门管理页面</title>
		<!--easyui支持引入  -->
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
		<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
		 $(function(){
			$('#ffemp').hide(); // 默认隐藏表单
			$('#dg').show(); // 默认显示表格
		}); 
		
		/**  部门分页列表  **/
		$(function(){
			$('#dg').datagrid({    
			    url:'findPageAll_Dep.do', // 访问路径
			    pagination:true, // 如果为true，则在DataGrid控件底部显示分页工具栏
			    pageNumber:1, // 在设置分页属性的时候初始化页码
			    pageSize:5, // 在设置分页属性的时候初始化页面大小
			    pageList:[3, 5, 7, 10], // 在设置分页属性的时候 初始化页面大小选择列表
			    striped:true, // 是否显示斑马线效果
			    columns:[[    
			    	{field:'depid', title:'部门编号', width:100, align:'center'},
			        {field:'depname', title:'部门名称', width:100, align:'center'},
			        {field:'opt', title:'操作', width:200, align:'center',
			        	formatter: function(value, row, index){
			        		var bt1 = "<input type='button' onclick=findById(" + row.depid + ") value='编辑'/>"
			        		return bt1;
			        	}
			        }
			    ]]    
			});  
		});
		/**  添加、编辑   **/
		function add() { // 添加
			$('#ffemp').show(); // 显示表单
			$('#btsave').show(); // 显示保存按钮
			$('#btdepupdate').hide(); // 隐藏修改按钮
			$('#ffe').hide(); // 隐藏表格
			$('#ffemp')[0].reset(); // 清空表单缓存
		}
		$(function(){
			$("#btsave").click(function(){
				$.messager.progress();	// 显示进度条
				$('#ffemp').form('submit', {
					url:'depsave_Dep.do',
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
							$('#dg').datagrid('reload');    // 重新载入当前页面数据 
							$('#ffemp').hide();  //员工管理页面关闭 
							$('#ffe').show(); // 显示表格
							

						}else{
							$.messager.alert('提示','保存失败!!!');
						}
						$.messager.progress('close');	// 如果提交成功则隐藏进度条
					}
				});
           });
			//修改
			$("#btdepupdate").click(function(){
				/* $('#ffemp').hide();  //员工管理页面隐藏 */
				$.messager.progress();	// 显示进度条
				$('#ffemp').form('submit', {
					url:'depupdate_Dep.do',
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
							$('#dg').datagrid('reload'); // 重新载入当前页面数据
							$('#ffemp').hide();// 隐藏表单
							$('#ffe').show(); // 显示表格

						}else{
							$.messager.alert('提示','修改失败!!!');
						}
						$.messager.progress('close');	// 如果提交成功则隐藏进度条
					}
				});


			});

			});
		function findById(depid){
			   alert("确认编辑");
			   $('#ffemp').show(); // 显示表单
				$('#btsave').hide(); // 显示保存按钮
				$('#btupdate').show(); // 隐藏修改按钮
				$('#ffe').hide(); // 隐藏表格
			  $.getJSON("find_dep.do?depid="+depid,function(dep){
				  //取消复选框中选中的类容
				  $(":checkbox[name='wids']").each(function(){
					  $(this).prop("checked",false);
				  });
				  $('#ffemp').form('load',{
						'depid':dep.depid,
						'depname':dep.depname,
					});
				});
			} 
		/**  页面跳转  **/
		$(function(){
			$("#btemp").click(function(){
				$(window).attr('location', "empadd.jsp");
			});
		});
		/** 页面跳转end **/
		</script>
	</head>
	<body>
	<form action="" name="ffe" id="ffe">
			<p align="center">部门列表</p>
			<hr/>
			<table id="dg"></table>
			<hr/>
			<p align="center">
				<input type="button" onclick="add()" id="btadd" name="btadd" value="添加部门">
				<input type="button" onclick="emp()" id="btemp" name="btemp" value="管理员工">
			</p>
		</form>
		<form action="" name="ffemp" id="ffemp" method="post" enctype="multipart/form-data">
			<table border="1px" width="550px" align="center">
				<tr bgcolor="#ffffcc" align="center">
					<td colspan="3">部门管理</td>
				</tr>
				<tr>
					<td>部门名称</td>
					<td colspan="2">
						<input type="text" id="depname" name="depname" class="easyui-validatebox" data-options="required:true">
					</td>
				</tr>
				<tr bgcolor="#FFFFCC" align="center">
					<td colspan="3">
						<input type="hidden" id="depid" name="depid">
						<input type="button" id="btsave" name="btsave" value="保存">
    					<input type="button" id="btdepupdate" name="btdepupdate" value="修改">
     					<input type="reset" id="btrest" name="btrest" value="取消">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>