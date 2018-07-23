import React from 'react';
import ReactDOM from 'react-dom';
import { Table, Divider } from 'antd';

const { Column, ColumnGroup } = Table;

export default class SchoolTable extends React.Component{

  onChange = (page) => {
    var that = this;
    that.props.useFormValueToRQ(page-1);
  }

	render(){
		return(
			<div {...this.props}>
				<Table dataSource={this.props.data} 
          pagination={{ pageSize:this.props.pageSize, total:this.props.total, onChange:this.onChange }}> 
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
