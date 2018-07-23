import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Input, Button } from 'antd';
import 'antd/dist/antd.css';
import './component.css';

const FormItem = Form.Item;

export default class SchoolSearchForm extends React.Component{

  componentDidMount(){
      this.props.onRef(this)
  }

  state = {
    values:{chara:"",
            cityName:"",
            pageSize:0,
            schoolId:0,
            schoolName:"",
            scoreline:0}
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        this.setState({values:values});
        this.netRequest(values,0);
      }
    });
  }

  netRequest = (values,pageNum) => {

        var that = this;
        var targetUrl = "http://192.168.0.144:8080/findSchool";
        fetch(targetUrl,{
            method: 'POST',
            headers: new Headers({
           'Content-Type': 'application/x-www-form-urlencoded'
        }),
           body: new URLSearchParams([["schoolId",values.schoolId],
                                      ["schoolName",values.schoolName],
                                      ["cityName",values.cityName],
                                      ["chara",values.chara],
                                      ["scoreline",values.scoreline],
                                      ["pageSize",values.pageSize],
                                      ["pageNum",pageNum]]),
        },
        ).then(function(response){
            return response.json().then(function(data){
              if (data.numberOfElements == 0) {
                 alert("没有相关数据🤪🙃🤔");
              }
              that.props.pushData2P(data);
            });
        })
    }

	render(){
    const { getFieldDecorator } = this.props.form;
		return(

		<Form onSubmit={this.handleSubmit} className="handle-form" >
        <FormItem>
        {getFieldDecorator('schoolId',{
            rules: [{ required: false }],
          })(
            <Input placeholder="School ID" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('schoolName',{
            rules: [{ required: false }],
          })(
            <Input placeholder="School Name" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('cityName',{
            rules: [{ required: false }],
          })(
            <Input placeholder="City" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('scoreline',{
            rules: [{ required: false }],
          })(
            <Input placeholder="scoreline <=" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('chara',{
            rules: [{ required: false }],
          })(
            <Input placeholder="CityChara" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('pageSize',{
            rules: [{ required: false }],
          })(
            <Input placeholder="PageSize" />
          )}
        </FormItem>
        <FormItem>
          <Button type="primary" htmlType="submit" className="handle-form-button">
            Search
          </Button>
        </FormItem>
      </Form>

		);
	}
}
