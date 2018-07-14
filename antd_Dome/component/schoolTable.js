import React from 'react';
import ReactDOM from 'react-dom';
import { Table, Divider } from 'antd';

const { Column, ColumnGroup } = Table;

const columns = [{
      title: 'ID',
      dataIndex: 'schoolId',
      key: 'schoolId'
  }, {
      title: 'name',
      dataIndex: 'schoolName',
      key: 'schoolName'
  }, {
      title: 'city',
      dataIndex: 'cityName',
      key: 'city'
  }, {
      title: 'scoreline',
      dataIndex: 'scoreline',
      key: 'scoreline'
      }
  ]

export default class SchoolTable extends React.Component{

  state = {
    lineData: ''
  };

  // componentDidUpdate(prevProps, prevState){
  //       this.setState(currentPage: this.props.current)
  //       //console.log('什么玩意？:',this.props.data.content);
  // }

  onChange = (page) => {
    //this.setState({current: page});
    var that = this;
    that.props.useFormValueToRQ(page-1);
  }
  


	render(){
		return(
			<div {...this.props}>
				<Table dataSource={this.props.data} pagination={{ pageSize:this.props.pageSize, total:this.props.total, onChange:this.onChange }}> 
          <Column
            title="ID"
            dataIndex="schoolId"
            key="schoolId"
          />
          <Column
            title="Name"
            dataIndex="schoolName"
            key="schoolName"
          />
          <ColumnGroup title="City">
            <Column
              title="cityName"
              dataIndex="cityName"
              key="cityName"
            />
            <Column
              title="Characteristics"
              dataIndex="chara"
              key="Characteristics"
            />
          </ColumnGroup>
          <Column
            title="Scoreline"
            dataIndex="scoreline"
            key="scoreline"
          />
        </Table>
			</div>
		);
	}
}

