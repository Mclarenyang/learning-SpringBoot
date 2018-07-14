import React from 'react';
import ReactDOM from 'react-dom';
import { LocaleProvider, DatePicker, message, Form } from 'antd';
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
// 由于 antd 组件的默认文案是英文，所以需要修改为中文
import zhCN from 'antd/lib/locale-provider/zh_CN';
import moment from 'moment';
import 'moment/locale/zh-cn';
import MyButton from './component/myButton';
import SchoolAddForm from './component/schoolAddForm';
import SchoolSearchForm from './component/schoolSearchForm';
import SchoolSearchIG from './component/schoolSearchGroup';
import SchoolTable from './component/schoolTable';

moment.locale('zh-cn');

const { Header, Content, Footer } = Layout;
const SubMenu = Menu.SubMenu;
const AddSchForm = Form.create()(SchoolAddForm);
const SearchSchForm = Form.create()(SchoolSearchForm);

class SiderDemo extends React.Component {


  state = {
    data: [],
    pageSize: 1,
    total:0,
    nowPage:0
  }

  onRef = (ref) => {
      this.child = ref
  }

  useFormValueToRQ = pageNum =>{
    console.log('page;',pageNum);
    //this.setState({nowPage: pageNum});
    this.child.netRequest(this.child.state.values, pageNum);

  }

  pushData2P = data => {

    var dataArray = new Array();
    for (var i = 0; i < data.numberOfElements; i++) {

      let schoolId = data.content[i].schoolId;   
      let schoolName = data.content[i].schoolName;
      let cityId = data.content[i].city.cityId;
      let cityName = data.content[i].city.cityName;

      let chara = "暂无数据";
      if (data.content[i].city.chara != ""){
        chara = data.content[i].city.chara; 
      }

      let scoreline = data.content[i].scoreline;

      dataArray.push({schoolId,schoolName,cityId,cityName,chara,scoreline});
    }
    console.log('dataArray', dataArray);
    this.setState({data: dataArray});
    this.setState({total: data.totalElements});
    this.setState({pageSize: data.size});
  }


  render() {
    return (
      <Layout>
        <Content style={{ margin: '20px 16px', maxHeight: 900}}>
          <div style={{ padding: 24, background: '#fff', minHeight: 700}}>
            <AddSchForm />
            <SearchSchForm pushData2P = {data => this.pushData2P(data)} onRef={this.onRef}/>
            <SchoolTable style={{
              position: 'relative',
              left: 400,
              top: -690,
              maxWidth: 850
            }}  data={this.state.data} total={this.state.total} pageSize={this.state.pageSize} useFormValueToRQ = {pageNum => this.useFormValueToRQ(pageNum)} />
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>
          School & City ©2018 Created by mclarenyang
        </Footer>
      </Layout>
    );
  }
}

ReactDOM.render(<SiderDemo />, document.getElementById("root"));
