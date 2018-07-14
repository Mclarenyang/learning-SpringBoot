import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Input, Button } from 'antd';
import 'antd/dist/antd.css';
import './component.css';


export default class SchoolSearchIG extends React.Component{

  netRequest = () => {

    console.log('zhixinzhici')

        var targetUrl = "http://192.168.0.133:8080/findSchool";
        fetch(targetUrl,{
            method: 'POST',
            headers: new Headers({
           'Content-Type': 'application/x-www-form-urlencoded' // 指定提交方式为表单提交
        }),
           body: new URLSearchParams([["schoolId",document.getElementById("schoolId").value],
                                      ["schoolName",document.getElementById("schoolName").value],
                                      ["cityName",document.getElementById("cityName").value],
                                      ["chara",document.getElementById("chara").value],
                                      ["scoreline",document.getElementById("scoreline").value],
                                      ["pageSize",document.getElementById("pageSize").value],
                                      ["pageNum",0]]),
        },
        ).then(function(response){
            return response.json().then(function(data){
              //console.log(data);
              //console.log('shenm:',data.content);
              this.props.pushData2P('thththeend')
            });
        })
    }


    //构造一个解析函数 然后子组件->父亲组件->子组件传递



	render(){

		return(
      <div className="login-form">
        <Input id ="schoolId" placeholder="School ID" />
        
        <Input id ="schoolName" placeholder="School Name" />
       
        <Input id ="cityName" placeholder="City" />
          
        <Input id ="scoreline" placeholder="scoreline <=" />
        
        <Input id ="chara" placeholder="CityChara" />
          
        <Input id ="pageSize" placeholder="PageSize" />
          
        <Button type="primary" htmlType="submit" className="login-form-button" onClick={this.netRequest}>
          Search
        </Button>
      </div>
       
		);
	}
}
