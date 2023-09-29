import styled from 'styled-components';

import Layout from '../../facades/Layout';

const StyledLayout = styled(Layout.Footer)`
  display: flex;
  justify-content: center;

  @media screen and (max-width: 425px) {
    font-size: 12px;
  }
`;

function Footer() {
  return (
    <StyledLayout>
      Prospect Web @2023 Created by Vitor Gusmão Loura
    </StyledLayout>
  );
}

export default Footer;
