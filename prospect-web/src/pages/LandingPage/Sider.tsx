import useBreakpoint from 'antd/es/grid/hooks/useBreakpoint';
import Layout from 'antd/es/layout';
import Menu from 'antd/es/menu';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

import colors from '../../colors';

const StyledSider = styled(Layout.Sider)`
  &.page-sider {
    background-color: ${colors.background};
    padding-top: 14px;
  }
`;

function Sider() {
  const breakpoints = useBreakpoint();

  console.log(breakpoints);

  return (
    <StyledSider
      className="page-sider"
      // breakpoint="lg"
      // collapsible={!breakpoints.lg}
      // collapsedWidth="80px"
      width="272px"
    >
      <Menu
        style={{ borderRightWidth: 0 }}
        mode="inline"
        items={[
          { key: 'create-prospect', label: 'Criar pré registro' },
          {
            key: 'list-prospects',
            label: <Link to="clients">Lista de pré registros</Link>,
          },
        ]}
      />
    </StyledSider>
  );
}

export default Sider;
