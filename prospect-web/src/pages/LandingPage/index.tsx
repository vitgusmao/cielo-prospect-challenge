import Card from 'antd/es/card/Card';
import useBreakpoint from 'antd/es/grid/hooks/useBreakpoint';
import Layout from 'antd/es/layout';
import Menu from 'antd/es/menu';
import Typography from 'antd/es/typography';
import styled from 'styled-components';

import colors from '../../colors';

const StyledLayout = styled(Layout)`
  height: 100%;
  display: flex;
  flex-direction: column;

  .page-header {
    background-color: ${colors.primaryColor};
    display: flex;

    .header-title {
      margin: 0;
      display: flex;
      justify-content: center;
      flex-direction: column;

      @media screen and (max-width: 498px) {
        font-size: 24px;
      }
      transition: font-size 0.3s ease;
    }
  }

  .page-sider {
    background-color: #ececec;
    padding-top: 14px;
  }

  .page-content {
    flex: 2;
    background-color: #5a646e2b;
    padding-top: 20px;
    padding-bottom: 20px;
    display: flex;
    justify-content: center;

    @media screen and (max-width: 400px) {
      padding-inline: 20px;
      transition: font-size 0.3s ease;
    }

    @media screen and (min-width: 400px) and (max-width: 768px) {
      padding-inline: 24px;
      transition: font-size 0.3s ease;
    }

    @media screen and (min-width: 768px) {
      padding-inline: 40px;
      transition: font-size 0.3s ease;
    }

    .content-holder {
      flex: 1;
      background-color: #ececec;
      @media screen and (min-width: 1024px) and (max-width: 1440px) {
        width: 800px;
      }
      @media screen and (min-width: 1440px) {
        width: 1000px;
      }
    }
  }

  .page-footer {
    display: flex;
    justify-content: center;

    @media screen and (max-width: 425px) {
      font-size: 12px;
    }
  }
`;

function LandingPage() {
  const { sm } = useBreakpoint();

  return (
    <StyledLayout>
      <Layout.Header className="page-header">
        <Typography.Title className="header-title" style={{ color: '#f0f0f0' }}>
          Portal de pré cadastros
        </Typography.Title>
      </Layout.Header>
      <Layout hasSider>
        <Layout.Sider
          className="page-sider"
          breakpoint="md"
          collapsedWidth={sm ? '40px' : '0'}
        >
          <Menu
            mode="inline"
            items={[
              { key: 'create-prospect', label: 'Criar pré registro' },
              { key: 'list-prospects', label: 'Lista de pré registros' },
            ]}
          />
        </Layout.Sider>
        <Layout.Content className="page-content">
          <Card className="content-holder" />
        </Layout.Content>
      </Layout>
      <Layout.Footer className="page-footer">
        Prospect Web @2023 Created by Vitor Gusmão Loura
      </Layout.Footer>
    </StyledLayout>
  );
}

export default LandingPage;
