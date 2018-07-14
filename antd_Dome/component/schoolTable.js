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

  componentDidUpdate(prevProps, prevState){
        console.log(this.props.data);
        //console.log('什么玩意？:',this.props.data.content);
  }
  


	render(){
		return(
			<div {...this.props}>
				<Table dataSource={this.props.data} > 
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

